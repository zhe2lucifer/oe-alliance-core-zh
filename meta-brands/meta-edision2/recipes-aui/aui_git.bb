SUMMARY = "ALi aui ${MACHINE}"
LICENSE = "CLOSED"
require conf/license/license-close.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "aliplatform pkgconfig"
RDEPENDS_${PN} = "aliplatform"

#SRC_URI = "git:///media/zhe22/git/alisdk/aui.git;branch=6.8.3.2-dev"
SRC_URI = "git://github.com/zhe2lucifer/aaa-zh.git;branch=6.8.3.2-dev"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

S = "${WORKDIR}/git"

inherit gitpkgv lib_package pkgconfig

EXTRA_OEMAKE = "\
    BUILDROOT_DISABLE_AUI_GFX_API=y \
    AUI_PACKAGE_ALIPLATFORM_INPUT=y \
    BUILDROOT_BUILD_AUI_VSC=n \
    BUILDROOT_BUILD_AUI_VMX_PLUS=n \
    BUILDROOT_BUILD_AUI_PVR=n \
    BUILDROOT_BUILD_AUI_MP=n \
    BUILDROOT_ENABLE_DEBUG=y \
    BUILDROOT_BUILD_AUI_VERSION=${SRCREV} \
    BUILDROOT_BUILD_AUI_SAMPLES=y \
    BUILDROOT_BUILD_AUI_NESTOR=n \
    BUILDROOT_BUILD_AUI_SFU_TEST=n \
    BUILDROOT=y \
    BUILDROOT_BUILD_AUI_LNB_BAND=C \
    BOARD_CFG=M3528 \
    AUI_BOARD_VERSION=01V01 \
    STAGING_DIR=${STAGING_INCDIR}/../../ \
    BR_CFLAGS="-I${STAGING_INCDIR}/ali_common -I${STAGING_INCDIR}/aliplatform" \
    OBJCPY=${OBJCOPY} \
"


do_install() {
    install -d ${D}${includedir}
    install -d ${D}${libdir}
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${datadir}/auiconfig
    install -d ${D}${bindir}
    install -m 0755 -D ${S}/output/linux/libaui* ${D}${libdir}
    install -m 0755 -D ${S}/inc/* ${D}${includedir}
    install -m 0755 -D ${S}/samples/resource/channel_change_info.ini ${D}${datadir}/auiconfig
    install -m 0755 -D ${S}/libaui.pc ${D}${libdir}/pkgconfig
}

FILES_${PN} = "${libdir} ${datadir}/auiconfig ${bindir}"

do_package_qa() {
}
