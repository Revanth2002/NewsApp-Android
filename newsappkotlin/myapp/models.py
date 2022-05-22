from django.db import models

# Create your models here.
class User(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=512,blank=True,null=True)
    phonenumber = models.CharField(max_length=20,blank=True,null=True)
    otp = models.CharField(max_length=10,blank=True,null=True)
    token = models.CharField(max_length=512,unique=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name