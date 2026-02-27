# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-LeszTom.git;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"
SRCREV = "0562336eeaa3b0dc9d69df0c5be471d3cfd232d9"
S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"
TARGET_LDFLAGS += "-lpthread -lrt"
RDEPENDS:${PN} += "bash"

INITSCRIPT_PACKAGES="${PN}"
INITSCRIPT_NAME:${PN}="aesdsocket-start-stop"
INITSCRIPT_PARAMS = "start 99 S 2 3 4 5 ."
inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d
}
