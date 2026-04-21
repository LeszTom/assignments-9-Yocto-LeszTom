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

SRC_URI = "git://git@github.com/LeszTom/assignments-7-LeszTom.git;protocol=ssh;branch=main \
file://S98lddmodules \
file://scull_load \
file://scull_unload \
"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "eac12e98859383c7647b9fc2a5687010897b9606"

S = "${WORKDIR}/git/scull"

inherit module
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
RDEPENDS:${PN} += "kernel-module-scull"

inherit update-rc.d
INITSCRIPT_PACKAGES="${PN}"
INITSCRIPT_NAME:${PN}="S98lddmodules"
INITSCRIPT_PARAMS = "defaults 98"

do_install:append () {
	install -d ${D}/home/root
	install -m 0755 ${WORKDIR}/scull_load ${D}/home/root
    install -m 0755 ${WORKDIR}/scull_unload ${D}/home/root

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/S98lddmodules ${D}${sysconfdir}/init.d/S98lddmodules
}

FILES:${PN} += " \
/home/root/scull_load \
/home/root/scull_unload \
${sysconfdir}/init.d/S98lddmodules \
"