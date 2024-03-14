# Terraform AWS EC2 Instance Provisioning

This Terraform script automates the provisioning of an Amazon EC2 instance in the specified region with the default profile.

## Terraform Configuration

### Provider Configuration

- **Provider**: Specifies the AWS provider with the required version and region configuration.

### Resources

#### `tls_private_key`

- **RSA Key Generation**: Generates a 4096-bit RSA private key.

#### `aws_key_pair`

- **Key Pair Creation**: Creates an AWS key pair using the generated RSA public key.

#### `local_file`

- **Private Key Storage**: Saves the generated RSA private key to a local file.

#### `aws_instance`

- **EC2 Instance**: Defines an EC2 instance with the specified AMI, instance type, and key pair.

### Variables

- **`key_name`**: Variable for specifying the name of the key pair.

### Outputs

- **`instance_id`**: Outputs the ID of the provisioned EC2 instance for reference.

## Prerequisites

- AWS CLI configured with appropriate credentials and permissions.
- Terraform CLI installed locally.

## Usage

1. Ensure that you have AWS credentials configured locally.
2. Install Terraform CLI if not already installed.
3. Customize the variables as needed in the Terraform script.
4. Run `terraform init` to initialize the working directory.
5. Run `terraform plan` to preview the changes.
6. Run `terraform apply` to apply the changes and provision the EC2 instance.
7. Upon successful provisioning, the instance ID will be displayed as an output.

## Note

- This script uses default AWS profile credentials. Modify the `profile` attribute in the provider block if using a different profile.
- Ensure that the specified AMI and region are appropriate for your use case.
- Store the private key securely as it grants access to the EC2 instance.

For more information on Terraform and AWS provider configurations, refer to the <a href="https://learn.hashicorp.com/tutorials/terraform/aws-build?in=terraform/aws-get-started">[Terraform documentation](https://learn.hashicorp.com/tutorials/terraform/aws-build?in=terraform/aws-get-started)</a>.<br><br><br>



# Terraform AWS IAM Role and EC2 Instance Configuration For S3 Bucket

This Terraform script automates the provisioning of an IAM role for EC2 instances and the creation of an EC2 instance in the specified AWS region.

## Terraform Configuration

### Provider Configuration

- **Provider**: Specifies the AWS provider with the required version and region configuration.

### Resources

#### `aws_iam_role`

- **IAM Role Creation**: Defines an IAM role named `s3use` with a trust policy allowing EC2 instances to assume the role.

#### `aws_iam_instance_profile`

- **Instance Profile Creation**: Associates the IAM role `s3use` with an instance profile named `web_instance_profile`.

#### `aws_iam_role_policy`

- **IAM Role Policy**: Defines a policy granting the IAM role `s3use` full access to Amazon S3 resources.

#### `aws_s3_bucket`

- **S3 Bucket Creation**: Creates an S3 bucket named `S3_Instance` with public read-write access.

#### `aws_instance`

- **EC2 Instance**: Defines an EC2 instance with the specified AMI, instance type, key pair, tags, and IAM instance profile.

### Variables

- **`key_name`**: Specifies the name of the key pair used for SSH access to the EC2 instance.

## Prerequisites

- AWS CLI configured with appropriate credentials and permissions.
- Terraform CLI installed locally.

## Usage

1. Ensure that you have AWS credentials configured locally.
2. Install Terraform CLI if not already installed.
3. Customize the variables as needed in the Terraform script.
4. Run `terraform init` to initialize the working directory.
5. Run `terraform plan` to preview the changes.
6. Run `terraform apply` to apply the changes and provision the IAM role and EC2 instance.

## Note

- This script uses default AWS profile credentials. Modify the `profile` attribute in the provider block if using a different profile.
- Ensure that the specified AMI and region are appropriate for your use case.
- Store the private key securely as it grants access to the EC2 instance.

For more information on Terraform and AWS provider configurations, refer to the  <a href="https://learn.hashicorp.com/tutorials/terraform/aws-build?in=terraform/aws-get-started">[Terraform documentation](https://learn.hashicorp.com/tutorials/terraform/aws-build?in=terraform/aws-get-started)</a>.<br><br><br>




# Build, Test, Deploy, and Run on EC2 USing GitHub Action

This GitHub Actions workflow automates the process of building, testing, deploying, and running a Java Spring Boot application on an EC2 instance. The workflow is triggered on pushes to the main branch.

## Workflow Overview

- **Build**: Compiles the Java project using Maven and packages it into a JAR file.
- **Test**: Runs the unit tests for the Java project.
- **Deploy and Run**: Deploys the built JAR file to an EC2 instance, stops any existing instance of the application, starts the new instance, and copies the JAR file to an S3 bucket for versioning.

## Workflow Steps

### Build

- **Checkout code**: Fetches the latest code from the repository.
- **Set up JDK 21**: Installs and configures JDK 21 for Java development.
- **Install Maven**: Installs Maven to manage project dependencies and build the project.
- **Build**: Executes the Maven command to clean the project and package it into a JAR file.
- **Upload JAR as artifact**: Uploads the generated JAR file as an artifact for later use.

### Test

- **Checkout code**: Fetches the latest code from the repository.
- **Set up JDK 21**: Installs and configures JDK 21 for Java development.
- **Run Tests**: Executes Maven command to run unit tests for the project.

### Deploy and Run

- **Download JAR artifact**: Downloads the previously built JAR artifact.
- **Remove previous JAR on EC2**: Removes any existing instance of the application running on the EC2 instance.
- **Copy JAR to EC2**: Copies the JAR artifact to the EC2 instance.
- **Run Spring Boot on EC2**: Starts the Spring Boot application on the EC2 instance by executing the JAR file, redirects output to a log file, and copies the JAR file to an S3 bucket for versioning.

## Prerequisites

- An EC2 instance set up with appropriate permissions to run the application.
- AWS credentials and permissions configured to interact with S3 for versioning the JAR file.

## Secrets Required

- `EC2_HOST`: The hostname or IP address of the EC2 instance.
- `EC2_USERNAME`: The username to access the EC2 instance.
- `EC2_PRIVATE_KEY`: The private key for SSH access to the EC2 instance.

## Note

Ensure that the necessary permissions and configurations are in place before using this workflow. This workflow assumes a specific directory structure and application setup on the EC2 instance. Customize the script according to your project's requirements and environment setup.

