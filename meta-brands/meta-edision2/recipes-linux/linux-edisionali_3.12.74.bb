SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit kernel machine_kernel_pr

KERNEL_RELEASE = "3.12.74"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

MACHINE_KERNEL_PR_append = ".0"

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

PR[vardepsxeclude]="DATETIME"

SRC_URI += "git:///media/zhe22/git/alisdk/linux-kernel.git;branch=6.8.3.2-dev \
    file://defconfig \
    "
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"

# do_rm_work() {
# 	:
# }

# do_configure() {
# 	:
# }
# 

do_compile () {
	unset TARGET_LDFLAGS TARGET_CXXFLAGS TARGET_CFLAGS LDFLAGS
	export PATH="/opt/toolchain/mips-2014.05/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin"

	export ARCH="mips"
	export ALIDRIVERS_DIR=""
	export ALI_KERNEL_CFLAGS="-DALI_CHIPPF_ -DALI_CHIPID_ -DALI_IC_ -DALI_BOARDTYPE_ -DALI_BOARDID_ -DDEV_FLASHBOOT_ -D__ALI_LINUX_KERNEL__  -D__NIM_LINUX_PLATFORM__ -D__NIM_ARM_PLATFORM__"
	export CROSS_COMPILE="mips-linux-gnu-"
	make -j 6 HOSTCC="/usr/bin/gcc" HOSTCFLAGS="" vmlinux
}

# extra tasks
# addtask kernel_link_images after do_compile before do_install
