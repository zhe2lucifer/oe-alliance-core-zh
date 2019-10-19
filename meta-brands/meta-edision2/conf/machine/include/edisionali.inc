# We know about rtc
include conf/machine/include/build-extras.inc

BRAND_OEM = "HDS"
MACHINE_BRAND = "HDS"

IMAGEDIR = "${MACHINE}"

MTD_KERNEL = "mtd2"
MTD_ROOTFS = "mtd0"
KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

MACHINE_EXTRA_RRECOMMENDS += " \
    ${@bb.utils.contains("GST_VERSION", "1.0", "gstreamer1.0-plugin-dvbmediasink", "gst-plugin-dvbmediasink", d)} \
    "

include conf/machine/include/autoload-filesystems.inc

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

PREFERRED_PROVIDER_virtual/kernel = "linux-edisionali"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "ali-blindscan"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "ttyS0,115200n8"

IMAGE_FSTYPES ?= "ubi"

IMAGE_CMD_ubi_prepend = " \
    rm -rf ${IMAGE_ROOTFS}/boot/*; \
    rm -rf ${IMAGE_ROOTFS}/tmp/*; \
    "

# IMAGE_CMD_ubi_append = "\
#     mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
#     cp ${DEPLOY_DIR_IMAGE}/splash.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash.bin; \
#     mv ${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${ROOTFS_FILE}; \
#     gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
#     echo ${IMAGE_VER_STRING} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
#     echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
#     cd ${DEPLOY_DIR_IMAGE}; \
#     zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
#     rm -f ${DEPLOY_DIR_IMAGE}/*.ubi; \
#     rm -f ${DEPLOY_DIR_IMAGE}/*.ubifs; \
#     rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
#     rm -f ${DEPLOY_DIR_IMAGE}/.ubifs; \
#     rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
#     rm -Rf ${IMAGEDIR}; \
#     "

IMAGE_CMD_ubi_append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    cp ${DEPLOY_DIR_IMAGE}/splash.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash.bin; \
    mv ${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${ROOTFS_FILE}; \
    gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
    echo ${IMAGE_VER_STRING} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ubi; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ubifs; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ubifs; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -Rf ${IMAGEDIR}; \
    "

MACHINE_FEATURES += "alsa 3dtv switchoff hdmicec bootsplash omb blindscan-tbs linuxwifi boxmodel feature1 feature2"

BOOTLOGO_FILENAME = "splash.bin"

require conf/machine/include/tune-mips32.inc
require conf/machine/include/soc-family.inc