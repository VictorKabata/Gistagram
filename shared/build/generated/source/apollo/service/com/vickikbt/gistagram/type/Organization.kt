//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.1.0'.
//
package com.vickikbt.gistagram.type

import com.apollographql.apollo3.api.ObjectType

/**
 * An account on GitHub, with one or more owners, that has repositories, members and teams.
 */
public class Organization {
  public companion object {
    public val type: ObjectType = ObjectType(name = "Organization", implements = listOf(Actor.type,
        MemberStatusable.type, Node.type, PackageOwner.type, ProfileOwner.type,
        ProjectNextOwner.type, ProjectOwner.type, RepositoryDiscussionAuthor.type,
        RepositoryDiscussionCommentAuthor.type, RepositoryOwner.type, Sponsorable.type,
        UniformResourceLocatable.type))
  }
}
