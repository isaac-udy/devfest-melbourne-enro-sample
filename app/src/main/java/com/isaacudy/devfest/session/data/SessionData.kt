package com.isaacudy.devfest.session.data

import com.isaacudy.devfest.speaker.data.SpeakerData
import java.time.LocalDate

object SessionData {
    private val sessionStartTime = LocalDate
        .parse("2024-10-10")
        .atTime(9, 30)

    val aiFuture = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerAliceJohnson.id,
        title = "AI: The Future is Now",
        description = "Join us for a deep dive into the world of AI and machine learning. With over 10 years of experience, our expert will unravel the mysteries of AI, showcasing real-world applications and future trends. Get ready to be amazed by the power of intelligent machines!",
        dateTime = sessionStartTime.plusMinutes(25 * 1),
    )

    val webWonders = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerBobSmith.id,
        title = "Web Wonders: Front-End Magic",
        description = "Discover the secrets of front-end development with our seasoned web developer. From HTML to CSS and JavaScript, learn how to create stunning, responsive websites that captivate users. This session is a must for anyone looking to master the art of web development.",
        dateTime = sessionStartTime.plusMinutes(25 * 2),
    )

    val mobileMarvels = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerCarolWilliams.id,
        title = "Mobile Marvels: Crafting Intuitive Apps",
        description = "Dive into the world of mobile development with our guru. Learn how to create intuitive user experiences that keep users coming back for more. This session will cover best practices, design principles, and the latest trends in mobile app development.",
        dateTime = sessionStartTime.plusMinutes(25 * 3),
    )

    val devOpsDelight = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerDavidBrown.id,
        title = "DevOps Delight: Automate All the Things!",
        description = "Join our DevOps engineer for a fun and informative session on automation and continuous integration. Discover how to streamline your development process, reduce errors, and deploy faster. Get ready to automate all the things!",
        dateTime = sessionStartTime.plusMinutes(25 * 4),
    )

    val dataDreams = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerEveDavis.id,
        title = "Data Dreams: Uncovering Insights",
        description = "Explore the fascinating world of data science with our expert. Learn how to uncover hidden insights from large datasets and turn data into actionable intelligence. This session will cover data analysis techniques, tools, and real-world examples.",
        dateTime = sessionStartTime.plusMinutes(25 * 5),
    )

    val cloudChronicles = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerFrankMiller.id,
        title = "Cloud Chronicles: Mastering AWS and Azure",
        description = "Join our cloud computing specialist for a journey through the clouds. Learn how to leverage AWS and Azure to build scalable, reliable, and cost-effective solutions. This session will cover cloud architecture, best practices, and case studies.",
        dateTime = sessionStartTime.plusMinutes(25 * 6),
    )

    val cybersecuritySecrets = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerGraceWilson.id,
        title = "Cybersecurity Secrets: Protecting Your Digital Assets",
        description = "Discover the secrets of cybersecurity with our expert. Learn how to protect your digital assets from threats and vulnerabilities. This session will cover security best practices, threat detection, and response strategies.",
        dateTime = sessionStartTime.plusMinutes(25 * 7),
    )

    val fullStackFun = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerHankMoore.id,
        title = "Full-Stack Fun: Building Scalable Web Apps",
        description = "Join our full-stack developer for a session on building scalable web applications. Learn how to create robust, high-performance web apps that can handle any load. This session will cover front-end and back-end development, as well as deployment strategies.",
        dateTime = sessionStartTime.plusMinutes(25 * 8),
    )

    val blockchainBonanza = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerIvyTaylor.id,
        title = "Blockchain Bonanza: Decentralized Applications",
        description = "Dive into the world of blockchain with our enthusiast. Learn how to develop decentralized applications that are secure, transparent, and tamper-proof. This session will cover blockchain fundamentals, smart contracts, and real-world use cases.",
        dateTime = sessionStartTime.plusMinutes(25 * 9),
    )

    val architectsArsenal = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerJackAnderson.id,
        title = "Architect's Arsenal: Designing Robust Systems",
        description = "Join our software architect for a session on designing robust systems. Learn how to create scalable, reliable, and maintainable architectures that stand the test of time. This session will cover design principles, patterns, and best practices.",
        dateTime = sessionStartTime.plusMinutes(25 * 10),
    )

    val nlpNirvana = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerKathyThomas.id,
        title = "NLP Nirvana: The Cutting Edge of AI",
        description = "Explore the cutting edge of natural language processing with our AI researcher. Learn how to build intelligent systems that understand and generate human language. This session will cover NLP techniques, tools, and real-world applications.",
        dateTime = sessionStartTime.plusMinutes(25 * 11),
    )

    val responsiveRevolution = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerLeoJackson.id,
        title = "Responsive Revolution: Front-End Design",
        description = "Join our front-end developer for a session on creating responsive designs. Learn how to build websites that look great on any device, from desktops to smartphones. This session will cover responsive design principles, frameworks, and best practices.",
        dateTime = sessionStartTime.plusMinutes(25 * 12),
    )

    val appAdventures = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerMonaWhite.id,
        title = "App Adventures: Mobile Development Success",
        description = "Discover the secrets of successful mobile app development with our expert. Learn how to create apps that users love, from concept to launch. This session will cover design, development, testing, and deployment strategies.",
        dateTime = sessionStartTime.plusMinutes(25 * 13),
    )

    val infrastructureInsights = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerNateHarris.id,
        title = "Infrastructure Insights: DevOps Best Practices",
        description = "Join our DevOps practitioner for a session on infrastructure as code. Learn how to automate your infrastructure, improve reliability, and reduce downtime. This session will cover tools, techniques, and real-world examples.",
        dateTime = sessionStartTime.plusMinutes(25 * 14),
    )

    val dataPipelinePerfection = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerOliviaMartin.id,
        title = "Data Pipeline Perfection: ETL Processes",
        description = "Explore the world of data engineering with our expert. Learn how to build efficient data pipelines and ETL processes that handle large volumes of data. This session will cover tools, techniques, and best practices.",
        dateTime = sessionStartTime.plusMinutes(25 * 15),
    )

    val cloudConundrums = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerPaulThompson.id,
        title = "Cloud Conundrums: Multi-Cloud Mastery",
        description = "Join our cloud architect for a session on multi-cloud environments. Learn how to leverage multiple cloud providers to build resilient, scalable solutions. This session will cover architecture, best practices, and case studies.",
        dateTime = sessionStartTime.plusMinutes(25 * 16),
    )

    val securitySavvy = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerQuincyGarcia.id,
        title = "Security Savvy: Threat Detection and Response",
        description = "Discover the world of threat detection and response with our security analyst. Learn how to identify and mitigate security threats before they become problems. This session will cover tools, techniques, and real-world examples.",
        dateTime = sessionStartTime.plusMinutes(25 * 17),
    )

    val microservicesMagic = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerRachelMartinez.id,
        title = "Microservices Magic: Back-End Development",
        description = "Join our back-end developer for a session on microservices architecture. Learn how to build scalable, maintainable, and resilient back-end systems. This session will cover design principles, patterns, and best practices.",
        dateTime = sessionStartTime.plusMinutes(25 * 18),
    )

    val blockchainBrilliance = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerSteveRobinson.id,
        title = "Blockchain Brilliance: Financial Solutions",
        description = "Explore the world of blockchain with our developer. Learn how to create innovative financial solutions using decentralized technology. This session will cover blockchain fundamentals, smart contracts, and real-world applications.",
        dateTime = sessionStartTime.plusMinutes(25 * 19),
    )

    val openSourceOdyssey = Session(
        id = Session.Id.new(),
        speaker = SpeakerData.speakerTinaClark.id,
        title = "Open-Source Odyssey: Contributing to the Community",
        description = "Join our software engineer for a session on open-source contributions. Learn how to get involved in the open-source community, contribute to projects, and make a difference. This session will cover tools, techniques, and best practices.",
        dateTime = sessionStartTime.plusMinutes(25 * 20),
    )

    val allSessions = listOf(
        aiFuture,
        webWonders,
        mobileMarvels,
        devOpsDelight,
        dataDreams,
        cloudChronicles,
        cybersecuritySecrets,
        fullStackFun,
        blockchainBonanza,
        architectsArsenal,
        nlpNirvana,
        responsiveRevolution,
        appAdventures,
        infrastructureInsights,
        dataPipelinePerfection,
        cloudConundrums,
        securitySavvy,
        microservicesMagic,
        blockchainBrilliance,
        openSourceOdyssey,
    )
}