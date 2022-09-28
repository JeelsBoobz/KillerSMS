package com.jeelsboobz.killersms;

import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    public final static List<String> hookPackages = Arrays.asList(
            "com.google.android.apps.messaging");

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (hookPackages.contains(lpparam.packageName)) {
            try {
                Class<?> arkn = XposedHelpers.findClassIfExists("arkn", lpparam.classLoader);
                if (arkn != null) {
                    XposedBridge.hookAllMethods(arkn, "handleMessage", XC_MethodReplacement.returnConstant(null));
                }
                Class<?> arle = XposedHelpers.findClassIfExists("arle", lpparam.classLoader);
                if (arle != null) {
                    XposedBridge.hookAllMethods(arle, "A", XC_MethodReplacement.returnConstant(null));
                }
            } catch (Throwable ignored) {
            }
        }
    }
}
