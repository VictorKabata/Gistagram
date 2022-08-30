//
//  ShimViewController.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation
import AuthenticationServices

class ShimViewController: UIViewController, ASWebAuthenticationPresentationContextProviding
{
    func presentationAnchor(for session: ASWebAuthenticationSession) -> ASPresentationAnchor {
        // Perhaps I don't need the window object at all, and can just use:
        // return ASPresentationAnchor()
        return  ASPresentationAnchor()
    }
}
