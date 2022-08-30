//
//  MainViewModel.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//
import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class MainViewModel : ObservableObject{
    @Published var accessToken:String?
    @Published var appTheme: AppTheme = AppTheme.System
    
    @LazyKoin
    private var authRepository : AuthRepository
    @LazyKoin
    private var settingRepository : SettingsRepository
    
    

    
    func fetchProfile(){
        let handle = Task{
            do{
                let data = try await asyncFunction(for: authRepository.fetchUserProfileNative())
                print(data)
            } catch{
                print("Failed with error in fetchprofile: \(error)")
            }
        }
    }
    
    func observeToken(){
        let handle = Task {
            do {
                
                let nativeFlow = try await asyncFunction(for: authRepository.getAccessTokenNative())
                let stream = asyncStream(for: nativeFlow)
                for try await letters in stream {
                    
                    accessToken = letters?.accessToken ?? nil
                
                    
                  
                    print("""
Got random letters: \(letters)

""")
                    
                  //  print(accessToken)
                    
                
                }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
    
    
    func observeTheme(){
        Task{
            do{
                let nativeFlow = try await asyncFunction(for: settingRepository.getAppThemeNative())
                let stream = asyncStream(for:nativeFlow)
                for try await theme in stream{
                    
                    appTheme = appTheme.getTheme(value : Int(truncating: theme ?? 3))
                                        
                    print("the theme is \(String(describing: theme))")
                }
            }
        }
    }
}
