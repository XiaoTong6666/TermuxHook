package io.github.xiaotong6666.termuxdblclick

import android.util.Log
import io.github.xiaotong6666.termuxdblclick.hook.BaseHook
import io.github.xiaotong6666.termuxdblclick.hook.DoubleClickDrawerHook
import io.github.kyuubiran.ezxhelper.xposed.EzXposed
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.ModuleLoadedParam
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam
import io.github.libxposed.api.XposedModuleInterface.PackageReadyParam

const val TARGET_APP = "com.termux"

class MainHook : XposedModule() {

    override fun onModuleLoaded(param: ModuleLoadedParam) {
        EzXposed.initOnModuleLoaded(this, param)
    }

    override fun onPackageLoaded(param: PackageLoadedParam) {
        if (param.packageName != TARGET_APP) return
        EzXposed.initOnPackageLoaded(param)
    }

    override fun onPackageReady(param: PackageReadyParam) {
        if (param.packageName != TARGET_APP) return
        EzXposed.initOnPackageReady(param)
        initHooks(DoubleClickDrawerHook)
    }

    private fun initHooks(vararg hooks: BaseHook) {
        for (hook in hooks) {
            try {
                if (hook.isInit) continue
                hook.init()
                hook.isInit = true
            } catch (t: Throwable) {
                Log.e("MainHook", "Failed to initialize hook: ${hook.name}", t)
            }
        }
    }
}
