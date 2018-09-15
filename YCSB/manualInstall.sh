#!/bin/sh
sudo rm /tmp/monmap
sudo rm /tmp/ceph.mon.keyring

sudo systemctl stop ceph-osd@*
sudo systemctl stop ceph-man@*
sudo systemctl stop ceph-mon@cephnode1


# check it is possible
sudo ceph-volume lvm zap --destroy /var/lib/ceph/osd/ceph-*

cd ~/ceph/build
sudo make uninstall

ceph-deploy purge cephnode1 cephnode2 cephnode3
ceph-deploy purgedata cephnode1 cephnode2 cephnode3
ceph-deploy forgetkeys

##############################

cd ~/ceph/build
sudo make install

sudo mkdir -p /var/log/ceph
sudo chown -R ceph:ceph /var/log/ceph
sudo mkdir -p /etc/ceph

echo "[global]
    fsid = c16c8ea8-31ab-4940-b971-d2edf940de1c
    mon_initial_members = cephnode1
    mon_host = 192.168.1.31
    auth_cluster_required = cephx
    auth_service_required = cephx
    auth_client_required = cephx

[mon]
    mon allow pool delete = true
    debug mon = 20

[osd]
    debug osd = 20
    debug bluestore = 20
    debug bluefs = 20
    debug bdev = 20" > ceph.conf

sudo mv ceph.conf /etc/ceph/

sudo -u ceph ceph-authtool --create-keyring /tmp/ceph.mon.keyring --gen-key -n mon. --cap mon 'allow *'
sudo ceph-authtool --create-keyring /etc/ceph/ceph.client.admin.keyring --gen-key -n client.admin --set-uid=0 --cap mon 'allow *' --cap osd 'allow *' --cap mds 'allow *' --cap mgr 'allow *'
sudo mkdir -p /var/lib/ceph/bootstrap-osd
sudo chown -R ceph:ceph /var/lib/ceph
sudo -u ceph ceph-authtool --create-keyring /var/lib/ceph/bootstrap-osd/ceph.keyring --gen-key -n client.bootstrap-osd --cap mon 'profile bootstrap-osd'
sudo ceph-authtool /tmp/ceph.mon.keyring --import-keyring /etc/ceph/ceph.client.admin.keyring
sudo ceph-authtool /tmp/ceph.mon.keyring --import-keyring /var/lib/ceph/bootstrap-osd/ceph.keyring
sudo -u ceph monmaptool --create --add cephnode1 192.168.1.31 --fsid c16c8ea8-31ab-4940-b971-d2edf940de1c /tmp/monmap
sudo -u ceph mkdir -p /var/lib/ceph/mon/ceph-cephnode1
sudo -u ceph ceph-mon --mkfs -i cephnode1 --monmap /tmp/monmap --keyring /tmp/ceph.mon.keyring
sudo -u ceph touch /var/lib/ceph/mon/ceph-cephnode1/done
sudo chmod +r /etc/ceph/ceph.client.admin.keyring

sudo cp ~/study/ceph/systemd/system/* /lib/systemd/system/

sudo systemctl enable ceph.target
sudo systemctl enable ceph-mon@cephnode1
sudo systemctl start ceph-mon@cephnode1

sudo -u ceph mkdir -p /var/lib/ceph/mgr/ceph-cephnode1
ceph auth get-or-create mgr.cephnode1 mon 'allow profile mgr' osd 'allow *' mds 'allow *' > keyring
sudo chmod 600 keyring
sudo mv keyring /var/lib/ceph/mgr/ceph-cephnode1/
sudo chown -R ceph:ceph /var/lib/ceph/mgr
sudo systemctl start ceph-mgr@cephnode1

sudo sshpass -p 'node2' scp ~/study/ceph/systemd/system/* cephnode2@cephnode2:~/system
sudo sshpass -p 'node3' scp ~/study/ceph/systemd/system/* cephnode3@cephnode3:~/system
sudo sshpass -p 'node2' scp /etc/ceph/* cephnode2@cephnode2:~
sudo sshpass -p 'node3' scp /etc/ceph/* cephnode3@cephnode3:~
sudo sshpass -p 'node2' scp /var/lib/ceph/bootstrap-osd/ceph.keyring cephnode2@cephnode2:~
sudo sshpass -p 'node3' scp /var/lib/ceph/bootstrap-osd/ceph.keyring cephnode3@cephnode3:~


sudo -u ceph mkdir -p /var/lib/ceph/osd

ceph -s


##############################s

sudo ceph-volume lvm create --data /dev/sda