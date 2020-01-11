SUMMARY = "aliplatform ${MACHINE}"
LICENSE = "CLOSED"
require conf/license/license-close.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "pkgconfig"

SRC_URI = "git:///media/zhe22/git/alisdk/aliplatform.git;branch=6.8.3.2-dev"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

S = "${WORKDIR}/git"

inherit gitpkgv autotools

EXTRA_OECONF = " \
    --enable-silent-rules \
    CFLAGS="-I${STAGING_INCDIR}/ali_common" \
    --enable-debug=yes \
    --enable-examples=no \
    --enable-libdmx=yes \
    --enable-libdb=no \
    --enable-libconaxvsc=no \
    --enable-vmxplus=no \
    --enable-libsdec=yes \
    --enable-libcc=yes \
    --enable-libvbi=yes \
    --enable-libtrng=yes \
    --enable-libsmc=yes \
    --enable-libfake=yes \
    --enable-libscart=yes \
    --enable-libstandby=yes \
    --enable-libdsc=yes \
    --enable-libcic=yes \
    --enable-libstorage=yes \
    --enable-libsysinfo=yes \
    --enable-libsnd=yes \
    --enable-libnim=yes \
    --enable-libtsi=yes \
    --enable-libtsg=yes \
    --enable-libce=yes \
    --enable-libdis=yes \
    --enable-libvdec=yes \
    --enable-libwatchdog=yes \
    --enable-libupgrade_v3=no \
    --enable-libupgrade_v4=no \
    --enable-libupgradeota=no \
    --enable-libhdmi=yes \
    --enable-libotp=yes \
    --enable-libsoc=yes \
    --enable-libavsync=yes \
    --enable-libpe=no \
    --enable-libsbm=yes \
    --enable-libunity=no \
    --enable-tests=no \
    --enable-libinput=yes \
    --enable-libevent=yes \
    --enable-libi2c=yes \
"

FILES_${PN}-dev += "${libdir}/*.la"
