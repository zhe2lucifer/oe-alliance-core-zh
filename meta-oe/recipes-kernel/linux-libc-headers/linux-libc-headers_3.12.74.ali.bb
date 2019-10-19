require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "git:///media/zhe22/git/alisdk/linux-kernel.git;branch=6.8.3.2-dev"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${includedir}/ali_common
	install -d ${D}${includedir}/linux
	cp -rf ${S}/drivers/alidrivers/include/*.h ${D}${includedir}/ali_common
	cp -rf ${S}/drivers/alidrivers/include/alidefinition ${D}${includedir}/ali_common
	cp -rf ${S}/drivers/alidrivers/include/rpc_hld ${D}${includedir}/ali_common
	cp -rf ${S}/drivers/alidrivers/include/linux ${D}${includedir}/ali_common
	cp -a  ${S}/include/linux/fusion.h ${D}${includedir}/linux
	cp -a  ${S}/include/linux/bpf_common.h ${D}${includedir}/linux
	cp -rf ${S}/drivers/alidrivers/include/ali_mtd_common.h ${D}${includedir}
}
