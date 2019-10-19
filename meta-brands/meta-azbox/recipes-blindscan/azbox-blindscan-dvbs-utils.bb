SUMMARY = "Tool for DVB-S/S2 blindscan"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"
SRC_URI = "http://source.mynonpublic.com/azbox/azbox-blindscan-utils-${MACHINE}-${PV}.tar.bz2;name=azbox-blind-${MACHINE}"

SRC_URI[azbox-blind-azboxhd.md5sum] = "0f536da981f694d821ea4f9c18e660c2"
SRC_URI[azbox-blind-azboxhd.sha256sum] = "574f3622d83c04faeb1587f19a64e4b1235341915707c83fd18c3f8102b390c0"
SRC_URI[azbox-blind-azboxme.md5sum] = "0f536da981f694d821ea4f9c18e660c2"
SRC_URI[azbox-blind-azboxme.sha256sum] = "574f3622d83c04faeb1587f19a64e4b1235341915707c83fd18c3f8102b390c0"
SRC_URI[azbox-blind-azboxminime.md5sum] = "0f536da981f694d821ea4f9c18e660c2"
SRC_URI[azbox-blind-azboxminime.sha256sum] = "574f3622d83c04faeb1587f19a64e4b1235341915707c83fd18c3f8102b390c0"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES_${PN} += "virtual/blindscan-dvbs"

RREPLACES_${PN} += "azbox-blindscan-utils"
RCONFLICTS_${PN} += "azbox-blindscan-utils"

PV = "1.3"
PR = "r3"

S = "${WORKDIR}/blindscan-utils"

do_install() {
    install -d "${D}/${bindir}"
    install -m 0755 "${S}/avl_azbox_blindscan" "${D}/${bindir}"
}

do_prepare_recipe_sysroot[noexec] = "1"
INHIBIT_PACKAGE_STRIP = "1"
do_compile[noexec] = "1"
deltask do_populate_sysroot
