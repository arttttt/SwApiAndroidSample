import Foundation
import shared
import SwiftUI

class RootComponentViewImpl : PlatformRootComponentView {

    private let component: RootComponent

    override var uiViewController: UIViewController {
        UIHostingController(rootView: SwiftUIRootView(component: component))
    }

    init(rootComponent: RootComponent) {
        component = rootComponent
    }
}

struct SwiftUIRootView: View {

    private let component: RootComponent

    @ObservedObject
    private var childStack: ObservableValue<ChildStack<AnyObject, DecomposeComponent>>

    private var activeChild: DecomposeComponent { childStack.value.active.instance }

    init(component: RootComponent) {
        self.component = component
        childStack = ObservableValue(component.childStack)
    }


    var body: some View {

        switch activeChild {
        case let viewOwner as ViewOwner:
            viewOwner.view.uiViewController.swiftUIWrapper
        default: EmptyView()
        }
    }
}
