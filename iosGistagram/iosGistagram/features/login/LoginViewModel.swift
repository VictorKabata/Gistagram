//
//  LoginViewMOdel.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LoginViewModel :  ObservableObject{
    @LazyKoin
    var repo  : AuthRepository
    
    @Published var isLoading : Bool = false
    
    @Published var errorMessage : String? = nil
    
    func fetchAccessToken(code:String){
        isLoading = true
        Task{
            do{
                errorMessage = nil
                let result = try await asyncFunction(for: repo.fetchAccessTokenNative(code: code))
                if (result?.accessToken) != nil{
                    
                    fetchUserProfile()
                } else {
                    errorMessage = "Error Authorizing account please try again"
                }
                
            } catch{
                errorMessage = error.localizedDescription
                debugPrint("the error obtained while fetching the profile is \(error)")
            }
        }
        
        
    }
    
    
    func fetchUserProfile(){
        isLoading = true
        
        
        let _ = Task{
            
            do{
                errorMessage = nil
                _ = try await asyncFunction(for: repo.fetchUserProfileNative())
                isLoading = false
            } catch{
                errorMessage = error.localizedDescription
                debugPrint("the error obtained while fetching the profile is \(error)")
            }
        }
    }
    
    
}
