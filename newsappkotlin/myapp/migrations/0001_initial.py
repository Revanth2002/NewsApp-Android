# Generated by Django 4.0.4 on 2022-05-19 16:05

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(blank=True, max_length=512, null=True)),
                ('phonenumber', models.CharField(blank=True, max_length=20, null=True)),
                ('otp', models.CharField(blank=True, max_length=10, null=True)),
                ('token', models.CharField(max_length=512, unique=True)),
                ('created_at', models.DateTimeField(auto_now_add=True)),
                ('updated_at', models.DateTimeField(auto_now=True)),
            ],
        ),
    ]
