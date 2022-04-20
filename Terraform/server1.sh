#!/bin/bash

whoami
echo logged in as $USER.
echo in directory $PWD

sudo apt update

echo "installing MariaDB..."
# sudo yum install mysql -y
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

sudo mysql -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; UPDATE mysql.user SET plugin = 'mysql_native_password' WHERE user = 'root' AND plugin = 'unix_socket'; FLUSH PRIVILEGES;"
sudo systemctl restart  mariadb
sudo systemctl enable mariadb

sudo apt install git -y

echo "needs to be in root account"
cd root
sudo touch .ssh/known_hosts
sudo ssh-keyscan git.cardiff.ac.uk >> .ssh/known_hosts
sudo chmod 644 .ssh/known_hosts

echo "now needs to be in centos user directory"
cd /home/debian
cat << `EOF` >> gitlab_deploy_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
`EOF`
chmod 400 gitlab_deploy_keypair.key
sudo ssh-agent bash -c 'ssh-add gitlab_deploy_keypair.key; git clone  git@git.cardiff.ac.uk:c21106601/co-creative-community-knowledge-group-1.git' -y

cd co-creative-community-knowledge-group-1/
mysql -uroot -pcomsc < src/BuildDB.sql


echo "Installing Java 11..."
sudo apt install -y openjdk-11-jdk
java -version

sudo apt install -y curl unzip

echo "Installing gradle..."
curl -O https://downloads.gradle-dn.com/distributions/gradle-7.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-7.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.2/bin
gradle -v




gradle build
gradle bootrun
