variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
variable "name1" { default = "CommunityEngagement DB" }

variable "network" { default = "21106601_network" }   # you need to change this
variable "keypair" { default = "21106601_key_new" } # you need to change this

variable "pool" { default = "cscloud_private_floating" }
variable "server1_script" { default = "./server1.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_security_CE_DB" }
