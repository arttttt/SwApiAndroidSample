import Foundation
import UIKit
import shared
import SwiftUI

class HeroesListToolbarViewImpl : HeroesListToolbarView {
    var uiViewController: UIViewController = UIViewController()

    private(set) var events: Flow = BuildersKt.emptyFlow()

    func render(model: Any) {
    }
}

class HeroesListFeatureDependenciesImpl : HeroesListFeatureDependencies {
    
    var listViewFactory: (HeroesListComponent) -> HeroesListView
    
    var toolbarViewFactory: () -> HeroesListToolbarView
    
    init(listViewFactory: @escaping (HeroesListComponent) -> HeroesListView, toolbarViewFactory: @escaping () -> HeroesListToolbarView) {
        self.listViewFactory = listViewFactory
        self.toolbarViewFactory = toolbarViewFactory
    }
    
}

class HeroFeatureDependenciesImpl : HeroFeatureDependencies {
    var heroViewFactory: () -> HeroView
    
    init(heroViewFactory: @escaping () -> HeroView) {
        self.heroViewFactory = heroViewFactory
    }
    
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    private let SAVED_STATE = "savedState"
    
    private var rootComponentHolder: ComponentHolder<RootComponent>?
    
    override init() {
        UINavigationBar.appearance().barTintColor = .white
        UITabBar.appearance().barTintColor = .white

        HeroesListComponentHolder.shared.dependencyProvider = { () -> (HeroesListFeatureDependencies) in
            HeroesListFeatureDependenciesImpl(
                    listViewFactory: { component in HeroesListViewImpl(heroesListComponent: component) },
                    toolbarViewFactory: { HeroesListToolbarViewImpl() }
            )
        }
        
        HeroComponentHolder.shared.dependencyProvider = { () -> (HeroFeatureDependencies) in
            HeroFeatureDependenciesImpl(heroViewFactory: { HeroViewImpl() })
        }
    }
    
    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        let savedState = rootComponentHolder!.stateKeeper.save()
        CodingKt.encodeParcelable(coder, value: savedState, key: SAVED_STATE)
        return true
    }
    
    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        do {
            let savedState = try CodingKt.decodeParcelable(coder, key: SAVED_STATE) as! ParcelableParcelableContainer
            rootComponentHolder = createRootComponentHolder(savedState: savedState)
            return true
        } catch {
            return false
        }
    }
    
    func getRootHolder() -> ComponentHolder<RootComponent> {
        if (rootComponentHolder == nil) {
            rootComponentHolder = createRootComponentHolder(savedState: nil)
        }
        
        return rootComponentHolder!
    }

    private func createRootComponentHolder(savedState: ParcelableParcelableContainer?) -> ComponentHolder<RootComponent> {
        ComponentHolder(savedState: savedState) { context -> RootComponent in
            RootComponentImpl(componentContext: context, viewFactory: { component in RootComponentViewImpl(rootComponent: component) })
        }
    }
}
