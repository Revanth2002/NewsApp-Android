import re
from django.shortcuts import render

# Create your views here.
from .models import *
from .serializers import *

from rest_framework import status
from rest_framework.views import APIView
from rest_framework.permissions import AllowAny
from rest_framework.response import Response

import random
import uuid

def generate_otp():
    return str(random.randint(1000, 9999))

class RegisterUser(APIView):
    authentication_classes = []
    permission_classes = [AllowAny]

    def post(self, request, *args, **kwargs):
        data = request.data
        name = data.get('name',None)
        phonenumber = data.get('phonenumber',None)
        print(f"----Data Request Received : {data}")

        validate_arr = [None,""]
        if name in validate_arr or phonenumber in validate_arr:
            return Response({"message":"Please provide all the fields"},status=status.HTTP_404_NOT_FOUND)

        """Check if the phonenumber already exists"""
        user = User.objects.filter(phonenumber=phonenumber).first()
        if user is not None:
            return Response({"message":"USer already exists.Try login"},status=status.HTTP_404_NOT_FOUND)


        """Generate OTP"""
        get_otp = generate_otp()
        
        """Saving the User object"""
        try:

            generate_token = uuid.uuid4().hex
            while True:
                check_token = User.objects.filter(token=generate_token).first()
                if check_token is None:
                    break
                generate_token = uuid.uuid4().hex

            new_user = User.objects.create(name=name,phonenumber=phonenumber,otp=get_otp,token=generate_token)
            print(f"-------OTP is {get_otp}-------------")
            return Response({"message":"OTP sent to your phone number","otp":f"{get_otp}","phonenumber":f"{phonenumber}","token":f"{generate_token}"},status=status.HTTP_200_OK)
        
        except Exception as e:
            print(e)
            return Response({"message":"Something went wrong"},status=status.HTTP_404_NOT_FOUND)

class LoginUser(APIView):
    authentication_classes = []
    permission_classes = [AllowAny]

    def post(self, request, *args, **kwargs):
        data = request.data
        phonenumber = data.get('phonenumber',None)

        print(f"----Data Request Received inside login : {data}")

        validate_arr = [None,""]
        if phonenumber in validate_arr:
            return Response({"message":"Please provide all the fields"},status=status.HTTP_404_NOT_FOUND)

        """Check if the phonenumber already exists"""
        user = User.objects.filter(phonenumber=phonenumber).first()
        if user is None:
            return Response({"message":"User does not exists.Try register"},status=status.HTTP_404_NOT_FOUND)

        new_otp = generate_otp()

        user.otp = new_otp
        user.save()

        print(f"-------OTP is {new_otp}-------------")
        return Response({"message":"OTP sent to your phone number","otp":f"{new_otp}","phonenumber":f"{phonenumber}"},status=status.HTTP_200_OK)

class VerifyUser(APIView):
    authentication_classes = []
    permission_classes = [AllowAny]

    def post(self, request, *args, **kwargs):
        data = request.data
        phonenumber = data.get('phonenumber',None)
        otp = data.get('otp',None)
        print(f"----Data Request Received : {data}")
        
        validate_arr = [None,""]
        if phonenumber in validate_arr or otp in validate_arr:
            return Response({"message":"Please provide all the fields"},status=status.HTTP_404_NOT_FOUND)

        """Check if the phonenumber already exists"""
        user = User.objects.filter(phonenumber=phonenumber).first()
        if user is None:
            return Response({"message":"User does not exists.Try register"},status=status.HTTP_404_NOT_FOUND)

        if str(user.otp) == str(otp):
            get_token = user.token
            return Response({"message":"OTP verified","token":f"{get_token}"},status=status.HTTP_200_OK)
        else:
            return Response({"message":"OTP not verified"},status=status.HTTP_404_NOT_FOUND)
