DESCRIPTION = "Linux kernel for the linkdroid device"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS = "lzop-native virtual/${TARGET_PREFIX}gcc"

inherit kernel machine_kernel_pr

LOCALVERSION ?= ""
MACHINE_KERNEL_PR_append = ".6"
SRCDATE = "20190222"

SRC_URI[md5sum] = "fa0029795853d4f5f94b8c405014ef05"
SRC_URI[sha256sum] = "e7a74a3173f0b274117b25f37ba4c75ff7be2b7687dc2200ca72b0e396688647"

SRC_URI += "http://source.mynonpublic.com/linkdroid/linux-${PV}-${SRCDATE}.tar.gz \
    file://defconfig \
    file://${MACHINE}.dts \
    file://0001-log2-give-up-on-gcc-constant-optimizations.patch \
    file://0002-uaccess-dont-mark-register-as-const.patch \
    file://0003-makefile-disable-warnings.patch \
"

S = "${WORKDIR}/common"
B = "${WORKDIR}/build"

do_configure_prepend () {
   cp -f ${WORKDIR}/${MACHINE}.dts ${S}/arch/arm64/boot/dts/amlogic/
}

do_compile_prepend () {
  install -d ${B}/drivers/amlogic/amports/
  cp -fr ${S}/drivers/amlogic/amports/amstream.o  ${B}/drivers/amlogic/amports/
  install -d ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml.o  ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml_fe.o  ${B}/drivers/amlogic/dvb_tv/
  install -d ${B}/drivers/amlogic/dvb_tv/smartcard
  cp -fr ${S}/drivers/amlogic/dvb_tv/smartcard/smartcard.o  ${B}/drivers/amlogic/dvb_tv/smartcard/
  install -d ${B}/drivers/amlogic/dvb_tv/dm6k/
  cp -fr ${S}/drivers/amlogic/dvb_tv/dm6k/dm6000_fe.o  ${B}/drivers/amlogic/dvb_tv/dm6k/
  install -d ${B}/drivers/media/dvb-core/
  cp -fr ${S}/drivers/media/dvb-core/dvb-core.o  ${B}/drivers/media/dvb-core/
  install -d ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_dmic.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_g9tv.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_m8.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_notify.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_snd_iomap.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_codec.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_dai.o  ${B}/sound/soc/aml/m8/

  if test -n "${KERNEL_DEVICETREE}"; then
      for DTB in ${KERNEL_DEVICETREE}; do
          if echo ${DTB} | grep -q '/dts/'; then
              bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
              DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
          fi
          oe_runmake ${DTB}
      done
  # Create directory, this is needed for out of tree builds
  mkdir -p ${B}/arch/arm64/boot/dts/amlogic/
  fi
}

do_compile_append() {
    cp ${B}/arch/arm64/boot/dts/amlogic/${MACHINE}.dtb ${B}/arch/arm64/boot/
}

do_rm-work() {
}
