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

SRC_URI = " \
git://git@github.com/LeszTom/assignments-7-LeszTom.git;protocol=ssh;branch=main \
file://module_load \
file://module_unload \
"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "eac12e98859383c7647b9fc2a5687010897b9606"

S = "${WORKDIR}/git/misc-modules"

inherit module

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install:append () {
	install -d ${D}/home/root
	install -m 0755 ${WORKDIR}/module_load ${D}/home/root
    install -m 0755 ${WORKDIR}/module_unload ${D}/home/root
}

FILES:${PN} += " \
/home/root/module_load \
/home/root/module_unload \
"