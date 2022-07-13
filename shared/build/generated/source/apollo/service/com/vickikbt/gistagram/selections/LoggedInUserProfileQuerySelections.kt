//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.1.0'.
//
package com.vickikbt.gistagram.selections

import com.apollographql.apollo3.api.CompiledArgument
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CompiledFragment
import com.apollographql.apollo3.api.CompiledSelection
import com.apollographql.apollo3.api.CompiledVariable
import com.apollographql.apollo3.api.list
import com.apollographql.apollo3.api.notNull
import com.vickikbt.gistagram.type.DateTime
import com.vickikbt.gistagram.type.FollowerConnection
import com.vickikbt.gistagram.type.FollowingConnection
import com.vickikbt.gistagram.type.GraphQLBoolean
import com.vickikbt.gistagram.type.GraphQLID
import com.vickikbt.gistagram.type.GraphQLInt
import com.vickikbt.gistagram.type.GraphQLString
import com.vickikbt.gistagram.type.Language
import com.vickikbt.gistagram.type.LanguageConnection
import com.vickikbt.gistagram.type.Organization
import com.vickikbt.gistagram.type.OrganizationConnection
import com.vickikbt.gistagram.type.PinnableItem
import com.vickikbt.gistagram.type.PinnableItemConnection
import com.vickikbt.gistagram.type.Repository
import com.vickikbt.gistagram.type.RepositoryConnection
import com.vickikbt.gistagram.type.RepositoryOwner
import com.vickikbt.gistagram.type.URI
import com.vickikbt.gistagram.type.User
import kotlin.collections.List

public object LoggedInUserProfileQuerySelections {
  private val followers: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build()
      )

  private val following: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build()
      )

  private val nodes: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "avatarUrl",
          type = URI.type.notNull()
        ).build()
      )

  private val organizations: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "nodes",
          type = Organization.type.list()
        ).selections(nodes)
        .build()
      )

  private val onUser: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "avatarUrl",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "login",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type
        ).build()
      )

  private val owner: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "__typename",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledFragment.Builder(
          typeCondition = "User",
          possibleTypes = listOf("User")
        ).selections(onUser)
        .build()
      )

  private val nodes2: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "color",
          type = GraphQLString.type
        ).build()
      )

  private val languages: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "nodes",
          type = Language.type.list()
        ).selections(nodes2)
        .build()
      )

  private val onRepository: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "createdAt",
          type = DateTime.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "description",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "stargazerCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isPrivate",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isFork",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "forkCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "url",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "viewerHasStarred",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "resourcePath",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "owner",
          type = RepositoryOwner.type.notNull()
        ).selections(owner)
        .build(),
        CompiledField.Builder(
          name = "languages",
          type = LanguageConnection.type
        ).arguments(listOf(
          CompiledArgument("first", CompiledVariable("languagesCount"))
        ))
        .selections(languages)
        .build()
      )

  private val nodes1: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "__typename",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledFragment.Builder(
          typeCondition = "Repository",
          possibleTypes = listOf("Repository")
        ).selections(onRepository)
        .build()
      )

  private val pinnedItems: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "nodes",
          type = PinnableItem.type.list()
        ).selections(nodes1)
        .build()
      )

  private val onUser1: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "avatarUrl",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "login",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type
        ).build()
      )

  private val owner1: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "__typename",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledFragment.Builder(
          typeCondition = "User",
          possibleTypes = listOf("User")
        ).selections(onUser1)
        .build()
      )

  private val nodes4: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "color",
          type = GraphQLString.type
        ).build()
      )

  private val languages1: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "nodes",
          type = Language.type.list()
        ).selections(nodes4)
        .build()
      )

  private val nodes3: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "createdAt",
          type = DateTime.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "description",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "stargazerCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isPrivate",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isFork",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "forkCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "url",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "viewerHasStarred",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "resourcePath",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "owner",
          type = RepositoryOwner.type.notNull()
        ).selections(owner1)
        .build(),
        CompiledField.Builder(
          name = "languages",
          type = LanguageConnection.type
        ).arguments(listOf(
          CompiledArgument("first", CompiledVariable("languagesCount"))
        ))
        .selections(languages1)
        .build()
      )

  private val repositories: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "totalCount",
          type = GraphQLInt.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "nodes",
          type = Repository.type.list()
        ).selections(nodes3)
        .build()
      )

  private val viewer: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "id",
          type = GraphQLID.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "email",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "name",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "login",
          type = GraphQLString.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "avatarUrl",
          type = URI.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "bio",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "websiteUrl",
          type = URI.type
        ).build(),
        CompiledField.Builder(
          name = "company",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "location",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "twitterUsername",
          type = GraphQLString.type
        ).build(),
        CompiledField.Builder(
          name = "followers",
          type = FollowerConnection.type.notNull()
        ).selections(followers)
        .build(),
        CompiledField.Builder(
          name = "following",
          type = FollowingConnection.type.notNull()
        ).selections(following)
        .build(),
        CompiledField.Builder(
          name = "isFollowingViewer",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isViewer",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "isCampusExpert",
          type = GraphQLBoolean.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "createdAt",
          type = DateTime.type.notNull()
        ).build(),
        CompiledField.Builder(
          name = "organizations",
          type = OrganizationConnection.type.notNull()
        ).arguments(listOf(
          CompiledArgument("first", CompiledVariable("organizationsCount"))
        ))
        .selections(organizations)
        .build(),
        CompiledField.Builder(
          name = "pinnedItems",
          type = PinnableItemConnection.type.notNull()
        ).arguments(listOf(
          CompiledArgument("first", CompiledVariable("pinnedReposCount"))
        ))
        .selections(pinnedItems)
        .build(),
        CompiledField.Builder(
          name = "repositories",
          type = RepositoryConnection.type.notNull()
        ).arguments(listOf(
          CompiledArgument("first", CompiledVariable("reposCount")),
          CompiledArgument("isFork", CompiledVariable("isFork"))
        ))
        .selections(repositories)
        .build()
      )

  public val root: List<CompiledSelection> = listOf(
        CompiledField.Builder(
          name = "viewer",
          type = User.type.notNull()
        ).selections(viewer)
        .build()
      )
}