//
//  TabNavigation.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import SwiftUI

struct TabNavigation: View {
    var body: some View {
        TabView{
            
            HomeScreen()
                .tabItem{
                    Image(systemName: "house")
                }
            
            SearchScreen()
                .tabItem{
                    Image(systemName: "magnifyingglass")
                }
            FavoritesScreen()
                .tabItem{
                    Image(systemName: "heart")
                }
            ProfileScreen()
                .tabItem{
                    Image(systemName: "person.circle")
                }
            
        }
    }
}

struct TabNavigation_Previews: PreviewProvider {
    static var previews: some View {
        TabNavigation()
    }
}
