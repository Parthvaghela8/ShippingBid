terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "eu-west-1"
  profile = "default"  # Use the profile name from your AWS credentials file, or remove this line if you're using environment variables or IAM role
}


# Create an IAM role for the Web Servers.
resource "aws_iam_role" "s3use" {
  name = "s3use"

  assume_role_policy = jsonencode({
    "Version": "2012-10-17",
    "Statement": [
      {
        "Action": "sts:AssumeRole",
        "Principal": {
          "Service": "ec2.amazonaws.com"
        },
        "Effect": "Allow",
        "Sid": ""
      }
    ]
  })
}

resource "aws_iam_instance_profile" "web_instance_profile" {
  name = "web_instance_profile"
  role = aws_iam_role.s3use.name
}

resource "aws_iam_role_policy" "s3use_policy" {
  name = "s3use_policy"
  role = aws_iam_role.s3use.id

  policy = jsonencode({
    "Version": "2012-10-17",
    "Statement": [
      {
        "Action": "s3:*",
        "Effect": "Allow",
        "Resource": "*"
      }
    ]
  })
}

resource "aws_s3_bucket" "S3_Instance" {
  bucket = "S3_Instance"
  acl    = "public-read-write"

  tags = {
    Name = "bucket-name"
  }
}

resource "aws_instance" "ec2_instance" {
  ami           = "ami-0fc3317b37c1269d3"
  instance_type = "t2.micro"
  key_name      = "ssh_key"
  tags = {
    Name          = "ec2_instance"
    created-using = "terraform"
    owner         = "parth.vaghela@bbd.co.za"
  }
  iam_instance_profile = aws_iam_instance_profile.web_instance_profile.name
}
