import Foundation
import SwiftUI

struct SwiftUIWrapper: UIViewControllerRepresentable {
    typealias UIViewControllerType = UIViewController

    private let wrapped: UIViewControllerType

    init(uiViewController: UIViewControllerType) {
        wrapped = uiViewController
    }

    func makeUIViewController(context: Context) -> UIViewController {
        wrapped
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

extension UIViewController {

    var swiftUIWrapper: SwiftUIWrapper {
        SwiftUIWrapper(uiViewController: self)
    }
}
