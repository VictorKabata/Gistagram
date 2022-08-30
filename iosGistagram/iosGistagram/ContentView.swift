//
//  ContentView.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 29/08/2022.
//

import SwiftUI
import shared

struct ContentView: View {
    @StateObject var mainViewModel : MainViewModel = MainViewModel()
    
    
    var body: some View {
        
        ZStack{
        if mainViewModel.accessToken == nil {
            LoginScreen()
        }
        else{
            TabNavigation()
        }
        }.onAppear{
            mainViewModel.observeTheme()
            mainViewModel.observeToken()
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
