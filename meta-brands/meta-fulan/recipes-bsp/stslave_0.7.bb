inherit src_rpm

STLINUX_SH_UPD_SRPMS = "http://archive.stlinux.com/stlinux/2.4/updates/SRPMS"
STLINUX_STSLAVE = "stlinux24-target-stslave-${PV}-29.src.rpm"

SRC_URI[md5sum] = "5c9da7b5a3c25aafb31b4f4ed8581b19"
SRC_URI[sha256sum] = "8165d5c591454ca7f10780d228d29b08fceb2885f9289627640413586403e002"

SRC_URI = "${STLINUX_SH_UPD_SRPMS}/${STLINUX_STSLAVE} \
"

LOCAL_SRC = "\
            file://${WORKDIR}/stslave-0.7.tar.gz \
            file://${WORKDIR}/stslave-0.6.udev.patch;patch=1;pnum=1 \
            file://${WORKDIR}/stslave-0.7.fix_dump_and_disc_syst.patch;patch=1;pnum=1 \
            file://${WORKDIR}/stslave-0.7-buildfix.patch;patch=1;pnum=1 \
            file://${WORKDIR}/stslave-fix-getenv.patch;patch=1;pnum=1 \
            file://${WORKDIR}/stslave-0.7-empty_section.patch;patch=1;pnum=1 \
            file://${WORKDIR}/stslave-0.7-new-toolchain-support.patch;patch=1;pnum=1 \
            file://001-stslave-fix-Makefile.patch;patch=1;pnum=1 \
            file://002-stslave-conf.patch;patch=1;pnum=1 \
"



DESCRIPTION = "The Linux stslave command loads an ST2x ST2xx application in the target \
memory and trigger its execution. The program can handle different types of \
target devices (slaves from now on) and different slaves of the same type"

DEPENDS = "binutils"
SECTION = "console/utils"

PR = "r1"

S = "${WORKDIR}/stslave"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${S}/main.c;beginline=4;endline=6;md5=42efebf7b210788356068c5ce3c011a4"

do_install () {
     install -d ${D}${base_bindir}
     install -m 0644 ${S}/stslave ${D}${base_bindir}
     install -d  ${D}${sysconfdir}
     install -m 0644 ${S}/hotplug_example/stslave.conf ${D}${sysconfdir}
}


