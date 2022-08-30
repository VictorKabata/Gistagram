//
//  HomeViewModel.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//
//


import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class HomeViewModel:ObservableObject{
    
    @Published var userDetails: shared.User_?
    var  accessToken = ""
   
    @LazyKoin
    var authRepository:AuthRepository
    
   
    
    func observeUser(){
        
    
        
        let handle =    Task{
            do{
                
                
                let nativeFlow = try await asyncFunction(for: authRepository.getUserNative())

                let stream  = asyncStream(for:nativeFlow)
                print("the stream is \(stream)")
               for try await userStream in stream{
                   print("the stream user is \(userStream)")
                   userDetails = userStream
               }
           
                
            }catch {
                print("Failed with error: \(error)")
            }
        }
    }
    
    func observeToken(){
        let handle = Task {
            do {
                let nativeFlow = try await asyncFunction(for: authRepository.getAccessTokenNative())
                let stream = asyncStream(for: nativeFlow)
                for try await letters in stream {
                    
                    accessToken = letters?.accessToken ?? ""
                    
                  
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
    
    
    
    
    
}
