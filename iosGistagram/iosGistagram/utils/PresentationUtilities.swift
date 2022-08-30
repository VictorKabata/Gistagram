//
//  PresentationUtilities.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation

enum AppTheme : Int{
    case Dark = 1
    case Light = 2
    case System = 3
    
    func getTheme(value: Int) -> AppTheme{
        switch value {
    case 1:
         return AppTheme.Dark
    case 2:
        return AppTheme.Light
    default:
        return AppTheme.Dark
    }
    }
    
}
