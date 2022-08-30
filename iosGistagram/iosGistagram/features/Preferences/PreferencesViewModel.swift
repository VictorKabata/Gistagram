//
//  PreferencesViewModel.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class PreferencesViewModel:ObservableObject{
    
    
    @Published var appTheme : AppTheme = AppTheme.System
   @LazyKoin
    var settingsRepository:SettingsRepository
  
    
    func observeTheme(){
        
        _ = Task{
            
            
            do{
                let nativeFlow = try await asyncFunction(for: settingsRepository.getAppThemeNative())
                let stream  = asyncStream(for: nativeFlow)
                
                for try await theme in stream {
                    
                    if let theme  = theme{
                        appTheme = appTheme.getTheme(value: Int(truncating: theme))
                    }
                    
                    
                }
            }
        }
    }
    
    
    func setTheme(newTheme: Int){
        _ = Task{
            do{
                _ =  try await asyncFunction(for: settingsRepository.saveAppThemeNative(theme: Int32(newTheme)) )
            }
        }
    }
}
