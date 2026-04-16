# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-LeszTom.git;protocol=ssh;branch=main \
	file://S97aesdcharmodule.sh \
	file://drivertest.sh \
	file://sockettest.sh \
	file://test.sh \
	"

PV = "1.0+git${SRCPV}"
SRCREV = "e099aaf3a05a2d624f07ac39b0f538c7320acf72"
S = "${WORKDIR}/git/aesd-char-driver"

inherit module
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d
INITSCRIPT_PACKAGES="${PN}"
INITSCRIPT_NAME:${PN}="S97aesdcharmodule.sh"
INITSCRIPT_PARAMS = "defaults 97"

do_install:append () {
	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/S97aesdcharmodule.sh ${D}${sysconfdir}/init.d/S97aesdcharmodule.sh

	install -d ${D}/home/root
    install -m 0755 ${WORKDIR}/*.sh ${D}/home/root
}

FILES:${PN} += " \
${sysconfdir}/init.d/S97aesdcharmodule.sh \
/home/root/*.sh \
"