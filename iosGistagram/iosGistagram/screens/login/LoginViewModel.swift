//
//  LoginViewMOdel.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync


class LoginViewModel :  ObservableObject{
    @LazyKoin
    var repo  : AuthRepository
    
    @Published var errorMessage : String? = nil
    
    func fetchAccessToken(code:String){
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
        
        
        let _ = Task{
            
            do{
                errorMessage = nil
                _ = try await asyncFunction(for: repo.fetchUserProfileNative())
            } catch{
                errorMessage = error.localizedDescription
                debugPrint("the error obtained while fetching the profile is \(error)")
            }
        }
    }
    
    
}
