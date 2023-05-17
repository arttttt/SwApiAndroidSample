import Foundation
import SwiftUI
import shared

class HeroesListViewImpl : PlatformListComponentView {

    override var initialState: HeroesListViewModel {
        HeroesListViewModel(items: [])
    }

    override var uiViewController: UIViewController {
        UIHostingController(rootView: SwiftUIHeroesListView(component: component, state: models) { event in self.dispatch(event: event) })
    }

    init(heroesListComponent: HeroesListComponent) {
        super.init(component: heroesListComponent)
    }
}

struct SwiftUIHeroesListView: View {

    @ObservedObject
    private var state: ObservableValue<HeroesListViewModel>

    @ObservedObject
    private var dialogSlot: ObservableValue<ChildSlot<AnyObject, HeroInfoComponent>>

    private let component: HeroesListComponent

    private let dispatch: (HeroesListViewUiEvent) -> Void

    init(component: HeroesListComponent, state: Value<HeroesListViewModel>, dispatch: @escaping (HeroesListViewUiEvent) -> Void) {
        self.component = component
        self.dispatch = dispatch;
        self.state = ObservableValue(state)
        dialogSlot = ObservableValue(component.dialogSlot)
    }

    var body: some View {
        GeometryReader { proxy in
            NavigationView {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(state.value.items, id: \.self) { item in
                            switch item {
                            case let progress as ProgressListItem.Initial:
                                ProgressView().frame(width: proxy.size.width, height: proxy.size.height)
                            case let progress as ProgressListItem.Page:
                                ProgressView()
                                        .frame(width: proxy.size.width)
                                        .padding(.vertical, 8)
                            case let hero as HeroListItem:
                                Text(hero.name)
                                        .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
                                        .frame(width: proxy.size.width, alignment: .leading)
                                        .onTapGesture {
                                            dispatch(HeroesListViewUiEvent.HeroClicked(name: hero.name))
                                        }
                                        .onLongPressGesture {
                                            dispatch(HeroesListViewUiEvent.ShowInfoClicked(name: hero.name))
                                        }
                                        .onAppear {
                                            let thresholdIndex = state.value.items.firstIndex(of: hero) ?? 0

                                            if (thresholdIndex > state.value.items.count - 10) {
                                                dispatch(HeroesListViewUiEvent.NewPageRequired())
                                            }
                                        }
                            default: EmptyView()
                            }
                        }
                    }
                }
                        .alert(
                                item: dialogSlot.value.child?.instance,
                                onDismiss: { component in component.onDismissed() },
                                title: { component in Text(component.title) },
                                message: { component in Text(component.message) },
                                actions: { component in Button("OK", action: { component.onDismissed() }) }
                        )
                        .safeAreaInset(edge: .top) {
                            Color.clear
                                    .frame(height: 0)
                                    .background(.clear)
                        }
                        .navigationBarTitleDisplayMode(.inline)
                        .navigationBarTitle(Text("test title"))
            }
        }
    }
}

extension View {
    @ViewBuilder func alert<T, A>(
            item: T?,
            onDismiss: @escaping (T) -> Void,
            title: (T) -> Text,
            message: (T) -> Text,
            actions: (T) -> A
    ) -> some View where A : View {
        if let item = item {
            alert(
                    title(item),
                    isPresented: Binding(get: { true }, set: {_,_ in onDismiss(item) }),
                    actions: { actions(item) },
                    message: { message(item) }
            )
        } else {
            self
        }
    }
}
