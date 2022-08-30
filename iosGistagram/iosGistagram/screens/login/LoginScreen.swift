//
//  LoginScreen.swift
//  iosGistagram
//
//  Created by Michael Ndiritu on 30/08/2022.
//

import Foundation
import SwiftUI
import AuthenticationServices
import shared


struct LoginScreen : View {
    
    @StateObject var loginViewModel : LoginViewModel = LoginViewModel()
    
    let contextProvider = ShimViewController()
    // Initialize the session.

   
    
    var body: some View {
       
            
           
        VStack{
            Spacer()
            Image("gitlogo")
                .resizable()
                .frame(width: 150, height: 150, alignment: .center)
            Spacer()
            Button(action: {
               fetchUserToken()
            }, label: {
                Text("LOGIN")
                    .font(.title2)
                    .bold()
                    .foregroundColor(.primary)
                    .padding(.horizontal,100)
                        .padding(.vertical,4).background(Color.black)
                                    
                                   
                                   
                   
            })
                .cornerRadius(4)
           
            Text("Powered by")
                .padding(.top, 16)
            
        Text("GitHub API")
                .bold()
                .font(.title2)
        }.frame(width: UIScreen.main.bounds.width,
                alignment: .bottom)
          
            
                
                
       
          
    
}
    
    func fetchUserToken(){
        let session = ASWebAuthenticationSession(url: URL(string: shared.Constants.shared.WEB_URL
                                                         )!, callbackURLScheme:"vickikbt"){callback,error in
            if let callBackData = callback{
                let queryItems = URLComponents(string: callBackData.absoluteString)?.queryItems
                if let token = queryItems?.filter({ $0.name == "code" }).first?.value{
                    print("the token is \(token)")
                    loginViewModel.fetchAccessToken(code: token)
                    
                }
                
                
                
            
//                IosRepository().iosAuth.fetchAccessToken(code: token!, completionHandler: {tokens,errors in
//
//                       print("the returned \(tokens) and the error is \(errors)")
//
//                    if let token = tokens {
//
//                        LoginViewModel().fetchUserProfile()
//                    }
//
//                   })
                
                
                
            }
            
            print("callback items \(callback)")
            print("error is \(error)")
            
        }
        
        session.presentationContextProvider = contextProvider
        session.start()
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}

