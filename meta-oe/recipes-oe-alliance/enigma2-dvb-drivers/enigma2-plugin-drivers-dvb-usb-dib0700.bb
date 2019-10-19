SUMMARY = "USB DVB driver for dib0700 chipset"
inherit allarch

require conf/license/license-gplv2.inc

DVBPROVIDER ?= "kernel"

RRECOMMENDS_${PN} = " \
    ${DVBPROVIDER}-module-dvb-usb-dib0700 \
    ${DVBPROVIDER}-module-dvb-usb-dibusb-common \
    ${DVBPROVIDER}-module-dvb-usb-dibusb-mc \
    ${DVBPROVIDER}-module-dib0090 \
    ${DVBPROVIDER}-module-dib3000mb \
    ${DVBPROVIDER}-module-dib3000mc \
    ${DVBPROVIDER}-module-dibx000-common \
    ${DVBPROVIDER}-module-mt2060 \
    ${DVBPROVIDER}-module-dib7000m \
    ${DVBPROVIDER}-module-dib7000p \
    ${DVBPROVIDER}-module-dib0070 \
    ${DVBPROVIDER}-module-dib8000 \
    ${DVBPROVIDER}-module-dvb-usb \
    ${DVBPROVIDER}-module-tuner-xc2028 \
    ${DVBPROVIDER}-module-fc0013 \
    ${DVBPROVIDER}-module-mt2266 \
    ${DVBPROVIDER}-module-tda18250 \
    ${DVBPROVIDER}-module-mn88472 \
    firmware-dvb-usb-dib0700-1.20 \
    firmware-dvb-usb-dibusb-5.0.0.11 \
    firmware-dvb-usb-dibusb-6.0.0.8 \
    firmware-dvb-usb-dibusb-an2235-01 \
    firmware-xc3028-v27 \
    firmware-xc3028l-v36 \
    firmware-mn8847x \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"
