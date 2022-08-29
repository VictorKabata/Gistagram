//
//  iosGistagramApp.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 29/08/2022.
//

import SwiftUI
import shared

@main
struct iosGistagramApp: App {
    
    init(){
        KoinApplication.start()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
