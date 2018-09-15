#!/bin/sh
sudo systemctl stop ceph-osd@*

sudo ceph-volume lvm zap --destroy /var/lib/ceph/osd/ceph-*

cd ~/ceph/build
sudo make uninstall

##############################

cd ~/ceph/build
sudo make install

sudo mv ~/system/* /lib/systemd/system

sudo mkdir -p /var/lib/ceph/bootstrap-osd
sudo mv ~/ceph.keyring /var/lib/ceph/bootstrap-osd/
sudo chown -R ceph:ceph /var/lib/ceph

sudo mkdir -p /etc/ceph
sudo mv ~/ceph.client.admin.keyring /etc/ceph/
sudo mv ~/ceph.conf /etc/ceph/

##############################

sudo ceph-volume lvm create --data /dev/sda