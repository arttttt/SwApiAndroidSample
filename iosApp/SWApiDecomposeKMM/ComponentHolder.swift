import Foundation
import shared

class ComponentHolder<T : DecomposeComponent> {
    let lifecycle: LifecycleRegistry
    let stateKeeper: StateKeeperDispatcher
    let component: T

    init(savedState: ParcelableParcelableContainer?, factory: (ComponentContext) -> T) {
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: savedState)
        let component = factory(
                DefaultComponentContext(
                        lifecycle: lifecycle,
                        stateKeeper: stateKeeper,
                        instanceKeeper: nil,
                        backHandler: nil
                )
        )
        self.lifecycle = lifecycle
        self.stateKeeper = stateKeeper
        self.component = component

        lifecycle.onCreate()
    }

    deinit {
        lifecycle.onDestroy()
    }
}