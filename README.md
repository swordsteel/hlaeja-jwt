# Hlæja Common Messages

In quiet chambers of learning, where minds are aglow, A ledger of endorsements, the authenticity to bestow. Each certificate approved, with scrutiny and might, Their legitimacy confirmed, in the light of day and night. From manuscripts to databases, knowledge is refined, Librarians and experts, their judgment to define. The Approval Archive, a sacred trust to hold, A testament to credentials, where merit is told.

## Releasing library

Run `release.sh` script from `master` branch.

## Publishing library

### Publish library locally

```shell
./gradlew clean build publishToMavenLocal
```

### Publish library to repository

```shell
./gradlew clean build publish
```

### Global Settings

This services rely on a set of global settings to configure development environments. These settings, managed through Gradle properties or environment variables.

*Note: For more information on global properties, please refer to our [global settings](https://github.com/swordsteel/hlaeja-development/blob/master/doc/global_settings.md) documentation.*

#### Gradle Properties

```properties
repository.user=your_user
repository.token=your_token_value
```

#### Environment Variables

```properties
REPOSITORY_USER=your_user
REPOSITORY_TOKEN=your_token_value
```
