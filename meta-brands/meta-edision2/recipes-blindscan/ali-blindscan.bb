SUMMARY = "Blindscan dvb-s(2) satellites using edisionali devices"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = " \
    ${@bb.utils.contains("MACHINE_FEATURES", "aliaui", "aui" , "", d)} \
    "

RDEPENDS_${PN} = " \
    ${@bb.utils.contains("MACHINE_FEATURES", "aliaui", "aui" , "", d)} \
    "

#SRC_URI = "git:///media/zhe22/git/alisdk/blindscan.git;branch=master"
SRC_URI = "git://github.com/zhe2lucifer/blindscan-zh.git;branch=master"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
PR = "r0"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES_${PN} += "virtual/blindscan-dvbs"

S = "${WORKDIR}/git/"

do_install () {
	install -d ${D}/${bindir}
	install -m 755 ${S}/ali_blindscan ${D}/${bindir}
}

INSANE_SKIP_${PN} += "dev-deps"
