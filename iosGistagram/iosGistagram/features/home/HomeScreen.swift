//
//  HomeScreen.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import SwiftUI

struct HomeScreen: View {
    @StateObject
    var homeViewModel:HomeViewModel = HomeViewModel()
    var body: some View {
        
        VStack{
            
            
            
            if let theUser = homeViewModel.userDetails{
            Text("\(theUser)")
                
             
            }else {
                Text("Could Not Load Details")
            }
            
        
       
            Spacer()
        }.onAppear{
          homeViewModel.observeUser()
            homeViewModel.observeToken()
        }.padding(2)
        
       
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
