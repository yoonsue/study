git clone https://github.com/ceph/ceph
git submodule update --init --recursive

./install-deps.sh

./do_cmake.sh

cd build

make vstart

../src/vstart.sh --debug --new -x --localhost --bluestore

./bin/ceph -s			# ceph start

./bin/rados lspoools	# check the pool list
./bin/rados mkpool rbd	# if there is no rbd, create rbd pool
./bin/rados lspools		# check pool creation

./bin/rados -p rbd bench 30 write

