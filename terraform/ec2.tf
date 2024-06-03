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
  region     = "eu-west-1"
  profile    = "default"
}

resource "tls_private_key" "rsa_4096" {
  algorithm   = "RSA"
  rsa_bits    = 4096
}

variable "key_name" {}

resource "aws_key_pair" "key_pair" {
  key_name   = var.key_name
  public_key = tls_private_key.rsa_4096.public_key_openssh
}

resource "local_file" "private_key" {
    content  = tls_private_key.rsa_4096.private_key_pem
    filename = var.key_name
}

resource "aws_instance" "ec2_instance" {
  ami           = "ami-0fc3317b37c1269d3"
  instance_type = "t2.micro"
  key_name      = aws_key_pair.key_pair.key_name

  tags = {
    Name          = "ec2_instance"
    created-using = "terraform"
    owner         = "parth.vaghela@bbd.co.za"
  }
}

# Output the instance ID for reference
output "instance_id" {
  value = aws_instance.ec2_instance.id
}
