SUMMARY = "bsp data"
LICENSE = "CLOSED"

require conf/license/license-close.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "1.0"
PR = "r0"

SRC_URI = "file://etc/network/interfaces \
	   file://etc/sign/plugin_dd_decoder.bin \
	   file://etc/RT2870STA.dat \
	   file://etc/RT2870STACard.dat \
	   file://etc/Wireless/RT2870STA/RT2860APCard.dat \
	   file://etc/Wireless/RT2870STA/RT2860AP.dat \
	   file://etc/Wireless/RT2870STA/RT2860AP.dat_ac \
	   file://etc/Wireless/RT2870STA/RT2860card.readme \
	   file://etc/Wireless/RT2870STA/RT2860STACard.dat \
	   file://etc/Wireless/RT2870STA/RT2860STA.dat \
	   file://etc/Wireless/RT2870STA/RT2870APCard.dat \
	   file://etc/Wireless/RT2870STA/RT2870AP.dat \
	   file://etc/Wireless/RT2870STA/RT2870STACard.dat \
	   file://etc/Wireless/RT2870STA/RT2870STA.dat \
	   file://etc/wpa_supplicant.ra0.conf \
	   file://usr/bin/aui_test \
	   file://usr/bin/fw_printenv \
	   file://usr/bin/fw_setenv \
	   file://usr/bin/gdb \
           "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/network
    install -d ${D}${sysconfdir}/sign
    install -d ${D}${sysconfdir}/Wireless/RT2870STA
    install -d ${D}${bindir}

    install -m 0755 ${S}/etc/network/interfaces ${D}${sysconfdir}/network
    install -m 0755 ${S}/etc/sign/plugin_dd_decoder.bin ${D}${sysconfdir}/sign
    install -m 0755 ${S}/etc/Wireless/RT2870STA/RT*.dat ${D}${sysconfdir}/Wireless/RT2870STA
    install -m 0755 ${S}/etc/RT2870STA.dat ${D}${sysconfdir}
    install -m 0755 ${S}/etc/RT2870STACard.dat ${D}${sysconfdir}
    install -m 0755 ${S}/etc/wpa_supplicant.ra0.conf ${D}${sysconfdir}
    install -m 0755 ${S}/usr/bin/aui_test ${D}${bindir}
    install -m 0755 ${S}/usr/bin/fw_printenv ${D}${bindir}
    install -m 0755 ${S}/usr/bin/fw_setenv ${D}${bindir}
    install -m 0755 ${S}/usr/bin/gdb ${D}${bindir}
}

do_package_qa() {
}
