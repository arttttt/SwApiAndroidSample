import SwiftUI
import shared

@main
struct SWApiDecomposeKMMApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    @Environment(\.scenePhase)
    var scenePhase: ScenePhase

    var rootHolder: ComponentHolder<RootComponent> {
        appDelegate.getRootHolder()
    }

    var body: some Scene {

        WindowGroup {
            rootHolder.component.view.uiViewController.swiftUIWrapper
                    .onChange(of: scenePhase) { newPhase in
                        switch newPhase {
                        case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
                        case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
                        case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
                        @unknown default: break
                        }
                    }
        }
    }
}