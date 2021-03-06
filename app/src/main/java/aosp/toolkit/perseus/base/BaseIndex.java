package aosp.toolkit.perseus.base;
/*
 * OsToolkit - Kotlin
 *
 * Date : 1/1/2019
 *
 * By   : 1552980358
 *
 */

import aosp.toolkit.perseus.BuildConfig;

public class BaseIndex {

    // Package Information/信息
    public final static String PackageName = BuildConfig.APPLICATION_ID;
    public final static String CoolapkPackageName = "com.coolapk.market";
    public final static String OsToolkitSubstratumName = "substratum.earth.OsToolkit";
    public final static String SubstratumName = "projekt.substratum";
    public final static int versionIndex = 0;
    public final static int OffLinePack = 107;

    // Coolapk / 酷安
    public final static String MaintainerCoolapkID = "724354";
    public final static String XzrID = "528761";
    public final static String YcID = "557898";

    public final static String Market_Head = "market://details?id=";
    public final static String Coolapk_Apk = "http://www.coolapk.com/apk/";
    public final static String Coolapk_User = "http://www.coolapk.com/u/";

    public final static String QQGroupKey = "Zbl6GscS86zcFBrelPvBW2lPcOPtqzt-";


    // Github
    public final static String Script_Head = "https://raw.githubusercontent.com/ToolkitPerseus/scripts/master/";

    public final static String Repo_Source = "https://github.com/1552980358/aosp.toolkit.perseus";
    public final static String Repo_Release = "https://github.com/ToolkitPerseus/aosp.toolkit.perseus/releases";


    // Path / 路径
    public final static String Charging_Allow = "/sys/class/power_supply/battery/battery_charging_enabled";
    public final static String Charging_QC3 = "/sys/class/power_supply/battery/allow_hvdcp3";
    public final static String Charging_USBQC = "/sys/kernel/fast_charge/force_fast_charge";

    public final static String io_random_emmc = "/sys/block/mmcblk0/queue/add_random";
    public final static String io_iostats_emmc = "/sys/block/mmcblk0/queue/iostats";
    public final static String io_nomerges_emmc = "/sys/block/mmcblk0/queue/nomerges";
    public final static String io_random_ufs = "/sys/block/sda/queue/add_random";
    public final static String io_iostats_ufs = "/sys/block/sda/queue/iostats";
    public final static String io_nomerges_ufs = "/sys/block/sda/queue/nomerges";


    // Command / 指令
    public final static String extends_freeform = "settings get global enable_freeform_support";

    // Type / 类型
    public final static String type_shell = "shell";
    public final static String type_yc = "yc";
    public final static String type_pixelcat = "PixelCat";


    // BaseIndex / 目录
    public final static String index_charging = "charging";
    public final static String index_romio = "romio";
    public final static String index_extends = "extends";


    // Shell / 脚本
    public final static String CHARGE_ALLOW = "charge_allow.sh";
    public final static String CHARGE_QC3 = "charge_qc3.sh";
    public final static String CHARGE_USBQC = "charge_usbqc.sh";

    public final static String IO_RANDOM_EMMC = "io_random_emmc.sh";
    public final static String IO_IOSTATS_EMMC = "io_iostats_emmc.sh";
    public final static String IO_NOMERGES_EMMC = "io_nomerges_emmc.sh";
    public final static String IO_RANDOM_UFS = "io_random_ufs.sh";
    public final static String IO_IOSTATS_UFS = "io_iostats_ufs.sh";
    public final static String IO_NOMERGES_UFS = "io_nomerges_ufs.sh";

    public final static String EXTENDS_FREE_FORM = "free_form.sh";
    public final static String EXTENDS_DETECT_SERVER = "detect_server.sh";

}
