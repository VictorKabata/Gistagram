//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.1.0'.
//
package com.vickikbt.gistagram.adapter

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.StringAdapter
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.vickikbt.gistagram.UserStatusQuery
import kotlin.IllegalStateException
import kotlin.Unit

public object UserStatusQuery_VariablesAdapter : Adapter<UserStatusQuery> {
  public override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters):
      UserStatusQuery = throw IllegalStateException("Input type used in output position")

  public override fun toJson(
    writer: JsonWriter,
    customScalarAdapters: CustomScalarAdapters,
    `value`: UserStatusQuery
  ): Unit {
    writer.name("userLogin")
    StringAdapter.toJson(writer, customScalarAdapters, value.userLogin)
  }
}
