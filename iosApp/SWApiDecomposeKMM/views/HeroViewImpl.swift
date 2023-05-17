import Foundation
import SwiftUI
import shared

class HeroViewImpl : PlatformHeroView {

    override var initialState: HeroViewModel {
        HeroViewModel(title: "")
    }

    override var uiViewController: UIViewController {
        UIHostingController(rootView: SwiftUIHeroView(state: models) { event in self.dispatch(event: event) })
    }
    
    override init() {
        print("hero view created")
        super.init()
    }
}

struct SwiftUIHeroView : View {

    @ObservedObject
    private var state: ObservableValue<HeroViewModel>

    private let dispatch: (HeroViewUiEvent) -> Void

    init(state: Value<HeroViewModel>, dispatch: @escaping (HeroViewUiEvent) -> Void) {
        self.state = ObservableValue(state)
        self.dispatch = dispatch
    }

    var body: some View {
        NavigationView {
            Text(state.value.title)
                    .navigationTitle(state.value.title)
                    .toolbar {
                        ToolbarItem(placement: .navigationBarLeading) {

                            Button(
                                    action: {
                                        dispatch(HeroViewUiEvent.BackPressed())
                                    },
                                    label: {
                                        HStack {

                                            Image(systemName: "chevron.backward")
                                            Text("Back")
                                        }
                                    }
                            )
                        }
                    }
        }
    }
}
