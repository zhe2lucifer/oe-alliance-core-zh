FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_gbmv200 = " \
     file://libjpeg.so.62.2.0 \
"

do_install_append_gbmv200() {
        install -d ${D}${libdir}
        install -m 0755 ${WORKDIR}/libjpeg.so.62.2.0 ${D}${libdir}/
}

