SUMMARY  = "Library to access Blu-Ray disk"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=435ed639f84d4585d93824e7da3d85da"

DEPENDS = "libxml2"

#SRC_URI = "gitsm://git.videolan.org/libbluray.git"
SRC_URI = "git://github.com/zhe2lucifer/libbluray-zh.git \
    file://expose_clip_id_in_BLURAY_CLIP_INFO.patch \
"

inherit gitpkgv autotools-brokensep pkgconfig

SRCREV = "${AUTOREV}"
PV = "v1.1.2+git${SRCPV}"
PKGV = "v1.1.2+git${GITPKGV}"

S="${WORKDIR}/git"

EXTRA_OECONF = " \
    --disable-bdjava-jar \
    --disable-doxygen-doc \
    --disable-doxygen-dot \
    --without-freetype \
    --without-fontconfig \
"
do_configure_prepend() {
    git submodule init
    git submodule update
}

do_package_qa() {
}
