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
    
    @Environment(\.colorScheme) var systemColorScheme: ColorScheme
    
    
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
            
        }.preferredColorScheme(colorScheme)
    }
    
    var colorScheme:ColorScheme{
        
        withAnimation{
        
        switch mainViewModel.appTheme{
        case .Dark:
            return .dark
            
        case .Light:
            return.light
        case .System:
            return systemColorScheme
            
        }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
