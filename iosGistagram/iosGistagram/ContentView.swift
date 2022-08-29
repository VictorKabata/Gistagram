//
//  ContentView.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 29/08/2022.
//

import SwiftUI
import shared

struct ContentView: View {
    
    let greetings = Platform().platform
    var body: some View {
        Text("Hello, world! from \(greetings)")
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
